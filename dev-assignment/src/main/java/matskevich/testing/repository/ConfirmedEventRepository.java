package matskevich.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import matskevich.testing.entity.ConfirmedEvent;

@Repository
public interface ConfirmedEventRepository extends JpaRepository<ConfirmedEvent, Long> {
}