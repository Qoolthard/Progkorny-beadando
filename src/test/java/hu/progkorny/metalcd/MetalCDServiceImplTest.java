package hu.progkorny.metalcd;

import hu.progkorny.metalcd.model.Genre;
import hu.progkorny.metalcd.model.Type;
import hu.progkorny.metalcd.model.MetalCD;
import hu.progkorny.metalcd.model.exception.NotFoundException;
import hu.progkorny.metalcd.service.impl.MetalCDServiceImpl;
import hu.progkorny.metalcd.service.MetalCDService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MetalCDServiceImplTest {

	private static final MetalCD NUMBER_OF_THE_BEAST_METALCD = new MetalCD(1L, "Number of the beast", Genre.HEAVY, Type.MAXI);
	private static final MetalCD TOXICITY_METALCD = new MetalCD(2L, "Toxicity", Genre.NUMETAL, Type.MINI);
	private static final MetalCD SILVERSIDE_METALCD = new MetalCD(3L, "Silverside", Genre.CLASSIC, Type.MINI);
	private static final List<MetalCD> ROLE_PLAYS = List.of(
			NUMBER_OF_THE_BEAST_METALCD,
			TOXICITY_METALCD,
			SILVERSIDE_METALCD
	);
	public static final long UNKNOWN_METALCD_ID = -1L;
	public static final String BLONDED_BY_METALCD_NAME = "Blonded by";

	private MetalCDService underTest;

	@BeforeEach
	void setUp() {
		underTest = new MetalCDServiceImpl(ROLE_PLAYS);
	}

	@Test
	void getAllMetalCDsShouldReturnAllMetalCDs() {
		// when
		final List<MetalCD> actual = underTest.getAllMetalCDs();
		// then
		assertThat(actual).isEqualTo(ROLE_PLAYS);
	}

	@Test
	void getMetalCDShouldReturnMetalCDWhenGivenIdOfExistingMetalCD() {
		// when
		final MetalCD actual = underTest.getMetalCD(TOXICITY_METALCD.getId());
		// then
		assertThat(actual).isEqualTo(TOXICITY_METALCD);
	}

	@Test
	void getMetalCDShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingMetalCD() {
		// when then
		assertThrows(NotFoundException.class, () -> underTest.getMetalCD(UNKNOWN_METALCD_ID));
	}

	@Test
	void createMetalCDShouldReturnRoleyPlayWhenDelegateIt() {
		// given
		final MetalCD blondedByMetalCD = new MetalCD(null, BLONDED_BY_METALCD_NAME, Genre.NUMETAL, Type.MAXI);
		final MetalCD expectedBlondeByMetalCD = new MetalCD(4L, BLONDED_BY_METALCD_NAME, Genre.NUMETAL, Type.MAXI);
		// when
		final MetalCD actual = underTest.createMetalCD(blondedByMetalCD);
		// then
		assertThat(actual).isEqualTo(expectedBlondeByMetalCD);
	}

	@Test
	void updateMetalCDShouldReturnUpdatedMetalCDWhenGivenIdOfExistingMetalCD() {
		// given
		final MetalCD blondedByMetalCD = new MetalCD(null, BLONDED_BY_METALCD_NAME, Genre.NUMETAL, Type.MAXI);
		final MetalCD expectedMetalCD = new MetalCD(TOXICITY_METALCD.getId(), BLONDED_BY_METALCD_NAME, Genre.NUMETAL, Type.MAXI);
		// when
		final MetalCD actual = underTest.updateMetalCD(TOXICITY_METALCD.getId(), blondedByMetalCD);
		// then
		assertThat(actual).isEqualTo(expectedMetalCD);
	}

	@Test
	void updateMetalCDShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingMetalCD() {
		// given
		final MetalCD blondedByMetalCD = new MetalCD(null, BLONDED_BY_METALCD_NAME, Genre.NUMETAL, Type.MAXI);
		// when then
		assertThrows(NotFoundException.class, () -> underTest.updateMetalCD(UNKNOWN_METALCD_ID, blondedByMetalCD));
	}

	@Test
	void deleteMetalCDShouldDeleteMetalCDWhenGivenIdOfMetalCD() {
		// given
		final List<MetalCD> expectedMetalCDs = List.of(TOXICITY_METALCD);
		// when
		underTest.deleteMetalCD(NUMBER_OF_THE_BEAST_METALCD.getId());
		underTest.deleteMetalCD(SILVERSIDE_METALCD.getId());
		final List<MetalCD> actual = underTest.getAllMetalCDs();
		// then
		assertThat(actual).isEqualTo(expectedMetalCDs);
	}
}