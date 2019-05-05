package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.TagDAO;
import cz.upce.votingsystemapplication.dto.TagDto;
import cz.upce.votingsystemapplication.model.Tag;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagDAO tagDAO;

    ModelMapper modelMapper = new ModelMapper();

    public void addTag(Tag tag) {
        tagDAO.save(tag);
    }

    public TagDto getTag(Long id) {
        Tag tag = tagDAO.getOne(id);
        return modelMapper.map(tag, TagDto.class);
    }

    public List<TagDto> getAllTag() {
        List<Tag> all = tagDAO.findAll();
        return modelMapper.map(all, new TypeToken<List<TagDto>>() {
        }.getType());
    }

    public void deleteTag(Long id) {
        tagDAO.deleteById(id);
    }

    public List<TagDto> findBySuggestion_Id(Long id) {
        List<Tag> tagOnSuggestion = tagDAO.findBySuggestion_Id(id);
        return tagOnSuggestion == null ? null : modelMapper.map(tagOnSuggestion, new TypeToken<List<TagDto>>() {
        }.getType());
    }
}
