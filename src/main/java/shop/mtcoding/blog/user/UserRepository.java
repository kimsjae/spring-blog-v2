package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", requestDTO.getUsername());
        query.setParameter("password", requestDTO.getPassword());
        return (User) query.getSingleResult();
    }
}
