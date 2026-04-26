package com.joalvarez.springpocaimodule;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = SpringPocAiModuleApplicationTest.class)
@Import({ObjectMapper.class})
public class SpringPocAiModuleApplicationTest {

    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

	/*
	 * TEST  E2E
	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

	// Ejecuta la aplicacion en un puerto ramdom :D
	@TestConstructor(autowireMode = ALL) // Evito usar autowired
	@ActiveProfiles("test") // Utilizo perfil test (application-test)
	@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Ni idea
	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) // Mantiene el contexto para todos los tests
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Habilita el orden de ejecucion de los metodos

	 *******

	 @ExtendsWith(MockitoExtension.class)
	 @InjectMocks
	 @Mock

	 ******

	 @DataJpaTest
	 @TestConstructor
	 @ActiveProfiles
	 @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
	*/

}