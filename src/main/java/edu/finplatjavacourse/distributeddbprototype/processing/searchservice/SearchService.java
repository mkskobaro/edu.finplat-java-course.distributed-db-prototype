package edu.finplatjavacourse.distributeddbprototype.processing.searchservice;


import edu.finplatjavacourse.distributeddbprototype.entity.Hotel;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.ReadStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ReadResponse;
import edu.finplatjavacourse.distributeddbprototype.processing.searchservice.idsearch.AVLTree;
import edu.finplatjavacourse.distributeddbprototype.processing.searchservice.substringsearch.SubstringSearch;
import edu.finplatjavacourse.distributeddbprototype.processing.searchservice.supportsearchservice.FileWorker;
import edu.finplatjavacourse.distributeddbprototype.processing.searchservice.supportsearchservice.HashWorker;

import java.util.List;

public class SearchService {
    private AVLTree tree = new AVLTree();

    private static SearchService instance;


    private SearchService() {

    }

    public  ReadResponse getStringFromCacheOrSearchSubstring(ReadStatement readStatement) {
        HashWorker.getInstance();
        List<Hotel> hashData = HashWorker.getInstance().getHashData(readStatement.searchOn());
        if (hashData != null)
            return ReadResponse.builder().hotels(hashData).build();
        else
            return ReadResponse.builder().hotels(substringSearch(readStatement.searchOn())).build();
    }

    public static SearchService getInstance() {

        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }

    public void addData(Hotel hotel, Long numberStr) {
        addIdIndexData(hotel.getId(), numberStr);
        addHashData(hotel);

    }

    private void addHashData(Hotel hotel) {
        HashWorker.getInstance().AddHash(hotel, findWord -> SubstringSearch.KMPSearch(findWord.toLowerCase().trim(), hotel.getName().toLowerCase().trim()));
    }

    private void addIdIndexData(Long id, Long numberStr) {
        tree.root = tree.insert(tree.root, id, numberStr);
    }

    public Hotel idSearch(int id) {
        Long numberStr = tree.search(id);

        if (numberStr == -1) return null;
        String line = FileWorker.getLine(numberStr);
        try {
            Hotel hotel = FileWorker.parseLine(line);
            return hotel;
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing data: " + e.getMessage());
            return null;
        }
    }

    public  List<Hotel> substringSearch(String findWord) {


        return FileWorker.getAllDataProcessedSubstringSearch(hotel -> SubstringSearch.KMPSearch(findWord.toLowerCase().trim(), hotel.getName().toLowerCase().trim()));
    }

    private Boolean containsSubstring(String findWord, String hotelName) {
        return SubstringSearch.KMPSearch(findWord, hotelName);
    }


}
