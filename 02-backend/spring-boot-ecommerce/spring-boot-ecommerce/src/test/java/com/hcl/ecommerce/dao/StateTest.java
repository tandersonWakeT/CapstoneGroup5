package com.hcl.ecommerce.dao;

import com.hcl.ecommerce.entity.Country;
import com.hcl.ecommerce.entity.State;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = {StateRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.hcl.ecommerce.entity"})
@DataJpaTest
public class StateTest {

    // Required Repos
    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private CountryRepository countryRepo;

    public StateTest() {
    }

    @BeforeEach
    public void setUp() {

        List<State> stateListUS = new ArrayList<>();
        List<State> stateListCA = new ArrayList<>();

        Country c1 = Country.builder()
                .code("US")
                .name("United States")
                .states(stateListUS)
                .build();
        Country c2 = Country.builder()
                .code("CA")
                .name("Canada")
                .states(stateListCA)
                .build();

        State s1 = State.builder()
                .name("North Carolina")
                .country(c1)
                .build();
        State s2 = State.builder()
                .name("South Carolina")
                .country(c1)
                .build();
        State s3 = State.builder()
                .name("Washington")
                .country(c1)
                .build();

        State s4 = State.builder()
                .name("Quebec")
                .country(c2)
                .build();
        State s5 = State.builder()
                .name("Ontario")
                .country(c2)
                .build();
        State s6 = State.builder()
                .name("Nova Scotia")
                .country(c2)
                .build();

        stateRepo.save(s1);
        stateRepo.save(s2);
        stateRepo.save(s3);
        stateRepo.save(s4);
        stateRepo.save(s5);
        stateRepo.save(s6);

        stateListUS.add(s1);
        stateListUS.add(s2);
        stateListUS.add(s3);

        stateListCA.add(s4);
        stateListCA.add(s5);
        stateListCA.add(s6);

        countryRepo.save(c1);
        countryRepo.save(c2);

        System.out.println("\n=========================\n");
    }

    @Test
    public void testStateRepoGet() {

        System.out.println("TEST GET CRUD OPERATION");

        String stateName = stateRepo.getById(1).getName();
        System.out.println("Get State By Id 1: " + stateName);

        Assertions.assertNotNull(stateRepo.getById(1));
    }

    @Test
    public void testFindByCountryCodeHappy() {

        System.out.println("TEST FIND BY COUNTRY CODE HAPPY PATH");

        String countryCode = "US";
        List<State> stateList = stateRepo.findByCountryCode(countryCode);

        for (State state : stateList) {

            System.out.println("State: " + state.getName() + "\nCountry: " + state.getCountry().getCode());
            Assertions.assertEquals("US", state.getCountry().getCode());
        }
    }

    @Test
    public void testFindByCountryCodeUnHappy() {

        System.out.println("TEST FIND BY COUNTRY CODE UNHAPPY PATH");

        String countryCode = "NA";
        List<State> stateList = stateRepo.findByCountryCode(countryCode);

        Assertions.assertEquals(0, stateList.size());
    }
}
