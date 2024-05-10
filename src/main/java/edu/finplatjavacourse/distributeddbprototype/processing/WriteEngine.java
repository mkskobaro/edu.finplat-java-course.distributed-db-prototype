package edu.finplatjavacourse.distributeddbprototype.processing;

import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.WriteStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.HashSet;

public class WriteEngine {

    // maybe
    // String filepath;
    private static WriteEngine instance;
    private static HashSet<Long> uniqueKeys = new HashSet<>();
    private static long lineNum = 0L;

    private WriteEngine(){}

    public static WriteEngine getInstance() throws IOException {
        if (instance == null){
            instance = new WriteEngine();
            try {
                RandomAccessFile dataFile = new RandomAccessFile("data.txt", "r");
                String line;
                while ((line = dataFile.readLine()) != null){
                    uniqueKeys.add(Long.parseLong(line.substring(0, line.indexOf(","))));
                    lineNum++;
                }
                dataFile.close();
            } catch (IOException ioExc) {
                throw new IOException("Error while reading unique keys from file");
            }
        }
        return instance;
    }

    public Response write(WriteStatement writeStatement) {
        if (!uniqueKeys.contains(writeStatement.id())){
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
            RandomAccessFile dataFile = new RandomAccessFile("data.txt", "rw");
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

            StringBuilder stringBuilder = null;
            if (lineNum == 0)
                stringBuilder = new StringBuilder();
            else
                stringBuilder = new StringBuilder("\n");

            stringBuilder.append(writeStatement.id())
                    .append(", \"")
                    .append(writeStatement.name())
                    .append("\", ")
                    .append(writeStatement.price());

            dataFile.seek(dataFile.length());
            dataFile.writeBytes(stringBuilder.toString());


            fileLock.release();
            dataFile.close();
            fileChannel.close();

            lineNum++;
        }
        catch (IOException ioExc){
            throw new IOException("Error while opening file to write");
        }
    }
}
