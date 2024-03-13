package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BoardRepository.class)
public class BoardPersistRepositoryTest {

    @Autowired // IoC에 있는 것을 D.I 해줌
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목수정1";

        // when
        Board board = boardRepository.findById(id);
        board.setTitle(title);
        em.flush();
    }

    @Test
    public void deleteById_test() {
        int id = 1;
        boardRepository.deleteById(id);
    }

    @Test
    public void deleteByIdV2_test() {
        // given
        int id = 1;

        // when
        boardRepository.deleteByIdV2(id);

        // 버퍼에 쥐고 있는 쿼리를 즉시 전송
        em.flush();

        // then
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.findById(id);
        em.clear();
        boardRepository.findById(id);
        System.out.println("findById_test : " + board);

        // then
        // org.assertj.core.api
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
//        assertThat(boardList.size()).isEqualTo(4);
//        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }


    @Test
    public void save_test() {
        // given
        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardRepository.save(board);
        System.out.println("save_test : " + board);

        // then
    }
}