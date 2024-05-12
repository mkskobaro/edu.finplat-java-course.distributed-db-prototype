package edu.finplatjavacourse.distributeddbprototype.processing;

import edu.finplatjavacourse.distributeddbprototype.entity.Hotel;
import edu.finplatjavacourse.distributeddbprototype.handler.exception.ExecutionException;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.CheckStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.WriteStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;
import edu.finplatjavacourse.distributeddbprototype.processing.searchservice.SearchService;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class WriteEngine {

    private final String FILEPATH = "data/hotel-list.txt";
    private static WriteEngine instance;
    private HashSet<Long> uniqueKeys = new HashSet<>();
    private long lineNum = 0L;

    private WriteEngine() {
        if (!Files.exists(Path.of("data"))) {
            try {
                Files.createDirectory(Path.of("data"));
            }
            catch (IOException ioExc) {
                throw new ExecutionException("Exception while creating directory", ioExc);
            }
        }
        try (RandomAccessFile dataFile = new RandomAccessFile(FILEPATH, "rw")) {
            String line;
            while ((line = dataFile.readLine()) != null) {
                uniqueKeys.add(Long.parseLong(line.substring(0, line.indexOf(","))));
                lineNum++;
            }
        } catch (IOException ioExc) {
            throw new ExecutionException("Exception while reading unique keys from file", ioExc);
        }
    }

    public static WriteEngine getInstance() {
        if (instance == null) {
            instance = new WriteEngine();
        }
        return instance;
    }

    public Response checkPutKey(CheckStatement checkStatement) {
        if (!uniqueKeys.contains(checkStatement.id())) {
            return Response.simpleResponse(true);
        }
        else {
            return Response.idAlreadyExistsResponse();
        }
    }

    public Response write(WriteStatement writeStatement) {
        if (!uniqueKeys.contains(writeStatement.id())) {
            try {
                writeToFile(writeStatement);
            }
            catch (IOException ioExc){
                return Response.simpleResponse(false);
            }
            return Response.simpleResponse(true);
        }
        else {
            return Response.idAlreadyExistsResponse();
        }
    }

    private void writeToFile(WriteStatement writeStatement) throws IOException {
        try (RandomAccessFile dataFile = new RandomAccessFile(FILEPATH, "rw")) {
            FileChannel fileChannel = dataFile.getChannel();
            FileLock fileLock;

            try {
                fileLock = fileChannel.tryLock();
            }
            catch (OverlappingFileLockException oflExc){
                dataFile.close();
                fileChannel.close();
                throw new OverlappingFileLockException();
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(writeStatement.id())
                        .append(", \"")
                        .append(writeStatement.name())
                        .append("\", ")
                        .append(writeStatement.price())
                        .append("\n");

            dataFile.seek(dataFile.length());
            dataFile.writeBytes(stringBuilder.toString());

            fileLock.release();
            fileChannel.close();

            uniqueKeys.add(writeStatement.id());
            lineNum++;
            SearchService.getInstance().addData(new Hotel(writeStatement.id(), writeStatement.name(), writeStatement.price()), lineNum);
        }
    }
}