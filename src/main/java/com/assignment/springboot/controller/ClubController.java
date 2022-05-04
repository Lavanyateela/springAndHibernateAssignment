package com.assignment.springboot.controller;


import com.assignment.springboot.entity.Club;
import com.assignment.springboot.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    private String clubForm="club-form";

    private String redirect="redirect:/clubs/list";

    @GetMapping("/list")
    public String getClubs(Model theModel)
    {
        theModel.addAttribute("clubs",clubService.findAll());
        return "list-clubs";
    }

    @GetMapping("/showFormForAdd")
    public String addNewClub(Model theModel)
    {
        theModel.addAttribute("club",new Club());
        return clubForm;
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("clubId") int clubId,Model theModel)
    {
        Club club=clubService.findById(clubId);
        theModel.addAttribute("club",club);
        return clubForm;
    }

    @PostMapping("/save")
    public String saveClub(@Valid @ModelAttribute("club") Club theClub, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return clubForm;
        }
        clubService.save(theClub);
        return redirect;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("clubId") int theId)
    {
        clubService.deleteById(theId);
        return redirect;
    }

}
