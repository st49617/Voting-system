package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.TagDto;
import cz.upce.votingsystemapplication.model.Tag;
import cz.upce.votingsystemapplication.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

        @Autowired
        TagService tagService;

        @PostMapping("add")
        public Tag addTag(@RequestBody Tag tag) {
            return tagService.addTag(tag);
        }

        @PostMapping("add-tag-suggestion/{tagId}/{suggestionId}")
        public String addTagToSuggestion(@PathVariable Long tagId, @PathVariable Long suggestionId) {
            tagService.addTagToSugestion(tagId, suggestionId);
            return "OK";
        }

        @PostMapping("remove-tag-suggestion/{tagId}/{suggestionId}")
        public String removeTagToSuggestion(@PathVariable Long tagId, @PathVariable Long suggestionId) {
            tagService.removeTagToSugestion(tagId, suggestionId);
            return "OK";
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
        private List<TagDto>  getTagOnSuggestion(@PathVariable Long suggestionId){
         return tagService.findBySuggestion_Id(suggestionId);
    }
}
