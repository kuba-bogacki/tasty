package template.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.courier.domain.Courier;
import template.courier.domain.type.CourierStatus;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID> {

    Optional<Courier> getFirstByStatusEquals(CourierStatus courierStatus);
}