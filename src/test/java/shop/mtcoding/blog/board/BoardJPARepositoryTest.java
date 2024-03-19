package shop.mtcoding.blog.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findByIdJoinUserAndReplies_test() {
        // given
        int id = 4;

        // when
        Board board = boardJPARepository.findByIdJoinUserAndReplies(id).get();

        // then
    }

    // save
    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder()
                .id(1)
                .build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        System.out.println("save_test : " + board.getId());
    }

    // findById
    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id); // null을 리턴받지 않기 위해 Optional을 쓴다.

        if (boardOP.isPresent()) { // 존재하면 꺼낸다. 이러면 NullPointException이 안 뜬다.
            Board board = boardOP.get();
            System.out.println("findById_test : " + board.getTitle());
        }

        // then
    }

    // findByIdJoinUser
    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;

        // when
        boardJPARepository.findByIdJoinUser(id);

        // then

    }

    // findAll (sort)
    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardJPARepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        // then
        System.out.println("findAll_test : " + boardList);
    }

    // findAll (pageable)
    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(1, 2, sort);

        // when
        Page<Board> boardPG = boardJPARepository.findAll(pageable);

        // then
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(boardPG);
        System.out.println(json);
    }

    // deleteById
    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);
        em.flush();

        // then
    }

}
