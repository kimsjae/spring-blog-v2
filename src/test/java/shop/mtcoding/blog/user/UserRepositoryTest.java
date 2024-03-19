package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest // Datasource(connection pool), EntityManager
@Import(UserJPARepository.class) // IoC 등록코드
public class UserRepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        userJPARepository.findById(id);

        // then
    }

//    @Test
//    public void findByUsername_test() {
//        // given
//        UserRequest.LoginDTO requestDTO = new UserRequest.LoginDTO();
//        requestDTO.setUsername("ssar");
//        requestDTO.setPassword("1234");
//
//        // when
//        User user = userJPARepository.findByUsernameAndPassword(requestDTO);
//
//        // then
//        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
//    }
}
