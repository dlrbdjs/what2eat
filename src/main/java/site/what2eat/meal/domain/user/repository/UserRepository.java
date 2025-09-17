package site.what2eat.meal.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.what2eat.meal.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
