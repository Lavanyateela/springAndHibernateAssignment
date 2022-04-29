package com.assignment.springboot.controller;

import com.assignment.springboot.dto.CollegeDTO;
import com.assignment.springboot.entity.Club;
import com.assignment.springboot.entity.College;
import com.assignment.springboot.service.ClubService;
import com.assignment.springboot.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    public CollegeService collegeService;

    public CollegeController(CollegeService theCollegeService)
    {
        collegeService=theCollegeService;
    }

    @GetMapping("/list")
    public String listCollege(Model theModel)
    {
        List<CollegeDTO> theCollege=collegeService.findAll();
        theModel.addAttribute("colleges",theCollege);

        return "colleges-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel)
    {
        College theCollege=new College();
        theModel.addAttribute("college",theCollege);

        return "college-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("collegeId") int theId, Model theModel)
    {
        Optional<College> theCollege= Optional.ofNullable(collegeService.findById(theId));

        theModel.addAttribute("college",theCollege);

        return "college-form";
    }

    @PostMapping("/save")
    public String saveCollege(@ModelAttribute("clubs") College theCollege, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "college-form";
        }
        collegeService.save(theCollege);

        return "redirect:/college/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("collegeId") int theId)
    {
        collegeService.deleteById(theId);

        return "redirect:/college/list";
    }

}
