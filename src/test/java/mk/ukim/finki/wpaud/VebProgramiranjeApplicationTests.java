package mk.ukim.finki.wpaud;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.enumerations.Role;
import mk.ukim.finki.wpaud.service.CategoryService;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import mk.ukim.finki.wpaud.service.ProductService;
import mk.ukim.finki.wpaud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class VebProgramiranjeApplicationTests {

    private static Category c1;
    private static Manufacturer m1;
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ManufacturerService manufacturerService;
    @Autowired
    ProductService productService;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        c1 = categoryService.create("c1", "c1 desc");
        m1 = manufacturerService.save("m1", "m1 address").get();

        userService.register("user1", "user", "user", "user", "user", Role.ROLE_USER);
        userService.register("admin1", "admin", "admin", "admin", "admin", Role.ROLE_ADMIN);
    }

    @Test
    public void testGetProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("pageNum", "1")
                        .param("pageSize", "10"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
//				.andExpect(model().attributeExists("page"))
//				.andExpect(model().attributeExists("manufacturers"))
//				.andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("bodyContent", "products"))
                .andExpect(view().name("master-template"));
    }

}

