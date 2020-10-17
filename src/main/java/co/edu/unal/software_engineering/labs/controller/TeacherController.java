package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.Period;
import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.labs.service.AssociationService;
import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.PeriodService;
import co.edu.unal.software_engineering.labs.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeacherController{

    private UserService userService;
    private CourseService courseService;
    private AssociationService associationService;
    private PeriodService periodService;

    public TeacherController(CourseService courseService, AssociationService associationService, UserService userService, PeriodService periodService) {
        this.courseService = courseService;
        this.associationService = associationService;
        this.userService = userService;
        this.periodService = periodService;
    }

    @PostMapping(value = { "/profesor/crear-curso" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCourse(@RequestBody CoursePOJO coursePOJO ){
        Course course = courseService.mapperCourseEntity(coursePOJO);

        if( course == null || !courseService.isRightCourse(course) ){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
        courseService.save(course);
        return new ResponseEntity<>( HttpStatus.CREATED );
    }

}
