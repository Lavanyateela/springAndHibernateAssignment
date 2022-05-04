package com.assignment.springboot.service;

        import com.assignment.springboot.entity.Club;
        import com.assignment.springboot.repository.ClubRepository;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.test.context.junit4.SpringRunner;

        import java.util.Optional;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClubServiceTest {
    @Autowired
    private ClubService clubService;

    @MockBean
    private ClubRepository clubRepository;

    @Test
    public void getAllClubs() {
        when(clubRepository.findAll())
                .thenReturn(Stream.of(new Club("ClubXD"))
                        .collect(Collectors.toList()));
        assertEquals(1, clubService.findAll().size());
    }

    @Test
    public void testFindById() {
        Club club = new Club(1, "XDXD Club", null);

        when(clubRepository.findById(1)).thenReturn(Optional.of(club));

        assertEquals(club, clubService.findById(1));
    }

    @Test
    public void testSaveClub() {
        Club club = new Club(1, "XDXD Club", null);
        when(clubRepository.save(club)).thenReturn(club);

        assertEquals(club, clubService.save(club));
    }

    @Test
    public void testDeleteClub() {
        Club club = new Club(1, "XDXD Club", null);
        clubService.deleteById(1);
        verify(clubRepository, times(1)).deleteById(1);
    }
}