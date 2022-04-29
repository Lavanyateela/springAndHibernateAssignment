package com.assignment.springboot.controller;

import com.assignment.springboot.dto.ClubDTO;
import com.assignment.springboot.dto.StudentDTO;
import com.assignment.springboot.entity.Student;
import com.assignment.springboot.service.ClubService;
import com.assignment.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClubService clubService;

    @GetMapping("/listByCollege")
    public String listStudentsByCollegeId(@RequestParam("collegeId") int id, Model theModel)
    {
        List<Student> students=studentService.findByCollegeId(id);
        theModel.addAttribute("students",students);
        theModel.addAttribute("collegeId",id);
        return "student-list-by-college";
    }

    @GetMapping("/list")
    public String getAllStudents(Model theModel)
    {
        List<StudentDTO> students=studentService.findAll();

        theModel.addAttribute("students",students);

        return "list-students";
    }

    @PostMapping("/showFormForAdd")
    public String showFormForAdd(@ModelAttribute("collegeId") int collegeId,Model model)
    {
        Student student=new Student();
        model.addAttribute("student",student);
        model.addAttribute("collegeId",collegeId);

        List<ClubDTO> clubs=clubService.findAll();
        model.addAttribute("clubs",clubs);
        return "student-form";
    }

    @PostMapping("/save")
    public String addNewStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                @ModelAttribute("collegeId") int collegeId,
                                Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("collegeId",collegeId);
            List<ClubDTO> clubs=clubService.findAll();
            model.addAttribute("clubs",clubs);
            return "student-form";
        }
        studentService.save(student,collegeId);
        return "redirect:/students/listByCollege?collegeId="+collegeId;
    }

    @PostMapping("/showFormForUpdate")
    public String  showFormForUpdate(@ModelAttribute("collegeId") int collegeId,@ModelAttribute("studentId") int studentId,Model model)
    {
        Student student=studentService.findById(studentId);
        model.addAttribute("student",student);
        List<ClubDTO> clubs=clubService.findAll();
        model.addAttribute("clubs",clubs);
        model.addAttribute("collegeId",collegeId);
        return "update-student-form";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student,@ModelAttribute("collegeId") int collegeId)
    {
        studentService.save(student,collegeId);
        return "redirect:/students/listByCollege?collegeId="+collegeId;
    }

    @PostMapping("/delete")
    public String deleteStudent(@ModelAttribute("studentId") int studentId,@ModelAttribute("collegeId") int collegeId)
    {
        studentService.deleteById(studentId);
        return "redirect:/students/listByCollege?collegeId="+collegeId;
    }
}
