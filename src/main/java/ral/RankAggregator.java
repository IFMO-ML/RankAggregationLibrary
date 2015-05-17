package ral;

import java.util.List;

public interface RankAggregator<T> {
    List<T> aggregate(ListOfRanks<T> ranks);
}
