package ru.otus.project.finance.financemonitorservice.util;

import java.util.ArrayList;
import java.util.List;

public class DataPartitioner {

    public static <T> List<List<T>> partitionData(List<T> sourceData, int partitionSize) {
        List<List<T>> partitions = new ArrayList<>();

        int dataSize = sourceData.size();
        for (int i = 0; i < dataSize; i += partitionSize) {
            int end = Math.min(i + partitionSize, dataSize);
            partitions.add(new ArrayList<>(sourceData.subList(i, end)));
        }

        return partitions;
    }
}