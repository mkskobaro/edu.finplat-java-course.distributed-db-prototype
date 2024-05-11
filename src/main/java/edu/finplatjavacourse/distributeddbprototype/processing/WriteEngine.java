package edu.finplatjavacourse.distributeddbprototype.processing;

import edu.finplatjavacourse.distributeddbprototype.handler.exception.ExecutionException;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.CheckStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.WriteStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class WriteEngine {

    private static final String FILEPATH = "data/hotel-list.txt";
    private static WriteEngine instance;
    private static HashSet<Long> uniqueKeys = new HashSet<>();
    private static long lineNum = 0L;

    private WriteEngine(){}

    public static WriteEngine getInstance() throws IOException {
        if (instance == null) {
            instance = new WriteEngine();
            try {
                if (!Files.exists(Path.of("data"))){
                    Files.createDirectory(Path.of("data"));
                }
                RandomAccessFile dataFile = new RandomAccessFile(FILEPATH, "rw");
                String line;
                while ((line = dataFile.readLine()) != null){
                    uniqueKeys.add(Long.parseLong(line.substring(0, line.indexOf(","))));
                    lineNum++;
                }
                dataFile.close();
            } catch (IOException ioExc) {
                throw new ExecutionException("Error while reading unique keys from file", ioExc);
            }
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
            //TODO: pass new key to search and line num
            return Response.simpleResponse(true);
        }
        else {
            return Response.idAlreadyExistsResponse();
        }
    }

    private void writeToFile(WriteStatement writeStatement) throws IOException {
        try {
            RandomAccessFile dataFile = new RandomAccessFile(FILEPATH, "rw");
            FileChannel fileChannel = dataFile.getChannel();
            FileLock fileLock = null;
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
            dataFile.close();
            fileChannel.close();
            uniqueKeys.add(writeStatement.id());
            lineNum++;
        }
        catch (IOException ioExc){
            throw new IOException("Error while opening file to write");
        }
    }
}