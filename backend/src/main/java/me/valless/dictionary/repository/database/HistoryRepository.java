package me.valless.dictionary.repository.database;

import me.valless.dictionary.entity.HistoryRow;
import me.valless.dictionary.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<HistoryRow, Long> {

    Page<HistoryRow> findAllByUser(User user, Pageable pageable);
}
