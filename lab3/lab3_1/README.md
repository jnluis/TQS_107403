# Lab3 Excercises

### Ex1

### a) Identify a couple of examples that use AssertJ expressive methods chaining.

- A_EmployeeRepositoryTest

```java
// Test of givenSetOfEmployees_whenFindAll_thenReturnAllEmployees()
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

- B_EmployeeService_UnitTest
```java
// Test of given3Employees_whengetAll_thenReturn3Records()
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```

- E_EmployeeRestControllerTemplateIT

```java
// Test of givenEmployees_whenGetEmployees_thenStatus200() 
assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
```

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

- B_EmployeeService_UnitTest.java

```java
@ExtendWith(MockitoExtension.class)
class B_EmployeeService_UnitTest {

    // mocking the responses of the repository (i.e., no database will be used)
    // lenient is required because we load more expectations in the setup
    // than those used in some tests. As an alternative, the expectations
    // could move into each test method and be trimmed (no need for lenient then)
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```

### c) What is the difference between standard @Mock and @MockBean?

The @Mock annotation comes from the Mockito library and allows the creation of a mock object of a class or an interface, so that a dummy functionality can be applied, isolating the unit of work taht is being tested. 

The @MockBean is used to mock objects in the Spring application context. This annotation is useful in integration tests where a particular bean, like an external service, needs to be mocked.
If a bean of the same type is already defined in the application context, @MockBean replaces it by a mock. If not, it adds a new mock bean.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

It defines the configuration properties that are going to be used on the integration tests, like DB's, etc.

### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

C uses the @MockBean anotation strategy, mocking the Employee Service. The D uses @AutoConfigureMockMvc anotation

D does not run a web server. The server is being mocked using @AutoConfigureMockMvc
@AutoConfigureTestDatabase, while in E a web server ise being used, assigning  a random port.