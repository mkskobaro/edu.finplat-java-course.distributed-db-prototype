package edu.finplatjavacourse.distributeddbprototype.processing.searchservice.supportsearchservice;


import edu.finplatjavacourse.distributeddbprototype.entity.Hotel;
import edu.finplatjavacourse.distributeddbprototype.processing.searchservice.SearchService;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashWorker {
    private static HashWorker instance;
    private HashMap<String, List<Hotel>> hashBaseRequest = new HashMap<>();

    private HashWorker() {

        List<String> baseKeys = Stream.of("hotel", "hostel", "inn", "lodge", "motel", "resort", "cottage", "villa")
                .collect(Collectors.toList());
        for (int i = 0; i < baseKeys.size(); i++) {
            hashBaseRequest.put(baseKeys.get(i), SearchService.getInstance().substringSearch(baseKeys.get(i)));
        }

    }
    public static HashWorker getInstance() {

        if (instance == null) {
            instance = new HashWorker();
        }
        return instance;
    }
    public List<Hotel> getHashData(String findWord){
        List<Hotel> hashData = hashBaseRequest.getOrDefault(findWord.toLowerCase().trim(), null);
        return hashData;
    }
    public void AddHash(Hotel hotel, Predicate<String> predicate){
        for (var curElement : hashBaseRequest.entrySet()) {
            if ( predicate.test(curElement.getKey())) {
                curElement.getValue().add(hotel);
            }
        }
    }
}
