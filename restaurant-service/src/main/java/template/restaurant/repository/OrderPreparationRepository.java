package template.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.restaurant.domain.OrderPreparation;

import java.util.UUID;

@Repository
public interface OrderPreparationRepository extends JpaRepository<OrderPreparation, UUID> {
}