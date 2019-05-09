package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.TagDto;
import cz.upce.votingsystemapplication.model.Tag;
import cz.upce.votingsystemapplication.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tag")
public class TagController {

        @Autowired
        TagService tagService;

        @PostMapping("add")
        public void addTag(@RequestBody Tag tag) {tagService.addTag(tag);
        }

        @GetMapping("get/{id}")
        public TagDto getTag(@PathVariable Long id) {
            return tagService.getTag(id);
        }

        @GetMapping("get-all")
        public List<TagDto> getTag() {
            return tagService.getAllTag();
        }

        @GetMapping("delete/{id}")
        public void deleteTag(@PathVariable Long id) {
            tagService.deleteTag(id);
        }

    @GetMapping("get-tag/{suggestionId}")
        private TagDto  getTagOnSuggestion(@PathVariable Long suggestionId){
         return tagService.findBySuggestion_Id(suggestionId);
    }
}
