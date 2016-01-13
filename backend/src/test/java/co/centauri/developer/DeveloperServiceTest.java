package co.centauri.developer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitils.reflectionassert.ReflectionAssert;

import co.centauri.exception.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    private DeveloperService developerService;

    private Developer developerKyloRen;
    private Developer developerDarthVader;
    private Developer developerDarthMaul;

    @Before
    public void init() {
        developerService = new DeveloperService();
        developerService.setRepository(developerRepository);
        developerKyloRen = DeveloperBuilder.createKyloRen();
        developerDarthVader = DeveloperBuilder.createDarthVader();
        developerDarthMaul = DeveloperBuilder.createDarthMaul();
    }

    @Test
    public void save() {
        developerService.save(developerKyloRen);
        verify(developerRepository, times(1)).save(developerKyloRen);
    }

    @Test
    public void findById() {
        Integer developerId = 1;
        when(developerRepository.findById(developerId)).thenReturn(developerKyloRen);

        Developer result = developerService.find(developerId);

        ReflectionAssert.assertReflectionEquals(developerKyloRen, result);
    }
    
    @Test(expected = NotFoundException.class)
    public void givenNonExistentDeveloperWhenCallFindIdThenThrowsNotFound() {
        Integer developerId = 25;
        when(developerRepository.findById(developerId)).thenReturn(null);
        developerService.find(developerId);
    }    

    @Test
    public void delete() {
        Integer developerId = 1;
        developerService.delete(developerId);
        verify(developerRepository, times(1)).delete(developerId);
    }

    @Test
    public void find() {
        List<Developer> expectedDevelopers = Arrays.asList(developerKyloRen, developerDarthMaul, developerDarthVader);        
        when(developerRepository.find()).thenReturn(expectedDevelopers);

        List<Developer> results = developerService.find();

        ReflectionAssert.assertReflectionEquals(expectedDevelopers, results);
    }    
}
