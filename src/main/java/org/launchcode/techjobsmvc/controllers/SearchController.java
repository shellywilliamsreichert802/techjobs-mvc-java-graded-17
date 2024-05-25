package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchType.equals("all")) {
            if (searchTerm == null || searchTerm.equals("")) {
                jobs = JobData.findAll();
                searchTerm = "";  // Set searchTerm to an empty string when it's null or empty
            } else {
                jobs = JobData.findByValue(searchTerm);
            }
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchTerm", searchTerm);  // Pass searchTerm to the view
        model.addAttribute("searchType", searchType);  // Pass searchType to the view
        return "search";
    }

//    @PostMapping(value = "results")
//    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
//        ArrayList<Job> jobs;
//        if (searchType.equals("all")) {
//            if (searchTerm == null || searchTerm.equals("")) {
//                jobs = JobData.findAll();
//                searchTerm = "";  // Set searchTerm to an empty string when it's null or empty
//            } else {
//                jobs = JobData.findByValue(searchTerm);
//            }
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//        }
//        model.addAttribute("jobs", jobs);
//        model.addAttribute("columns", ListController.columnChoices);
//        model.addAttribute("searchTerm", searchTerm);  // Pass searchTerm to the view
//        return "search";
//    }

}


