package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping ("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping ("results")
    public String displaySearchResults(Model model, @RequestParam String searchType,@RequestParam (required = false)String searchTerm){
        ArrayList<Job> jobs;

        if (searchTerm == null || searchTerm.isEmpty() || searchTerm.equalsIgnoreCase("all")) {
            // Call the findAll() method from JobData
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
            model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
        }

        // Now, you have the search results in the 'jobs' ArrayList
        model.addAttribute("jobs",jobs);
        model.addAttribute("columns",ListController.columnChoices);
        return "search";
    }
}