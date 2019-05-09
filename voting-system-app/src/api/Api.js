import axios from 'axios'

const SERVER_URL = 'http://localhost:8080';

const instance = axios.create({
    baseURL: SERVER_URL,
    timeout: 30000,
    withCredentials: true,
    // headers: {
    //     'Content-Type': 'application/json;charset=UTF-8',
    //     'Accept': 'text/plain',
    //     // 'Authorization': 'Basic c3VwOnN1cA==',
    // },

    // headers: {'Authorization': "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJickBzZXpuYW0uY3oiLCJyb2xlcyI6InVzZXIiLCJpYXQiOjE1NTcwNjYxMjh9.6mR9zHe_mw6yEQb5ZlfXiV-lj5pkSzFhpdlSYuj4RJw"}

});

function setToken(token) {
    $cookies.set('AUTH_TOKEN', token);
}

function getToken() {
    return $cookies.isKey('AUTH_TOKEN') ? $cookies.get('AUTH_TOKEN') : '';
}

export const userIsLogged = function () {
    return (getToken() !== '' && getToken() !== null);
}

export const userLogin = function (credentials) {
    return instance.post('api/user/login', credentials)

};


export const createNewMeeting = function (meeting) {
    let token = getToken();
    return instance.post('api/meeting/add', meeting, {headers: {'Authorization': "Bearer " + token}})
};


export const getAllMeetings = function () {
    let token = getToken();
    return instance.get('api/meeting/get-all', {headers: {'Authorization': "Bearer " + token}})
};

export const getSuggestion = function (suggestionId) {
    return instance.get('api/suggestion/get/' + suggestionId);
};

export const addSugesstion = function (suggestion) {
    return instance.post('api/suggestion/add', suggestion);
};

export const addVoting = function (voting) {
    let token = getToken();
    return instance.post('api/voting/add', voting, {headers: {'Authorization': "Bearer " + token}});
};

export const getVotingForSuggestion = function (suggestionId) {
    let token = getToken();
    return instance.get('api/voting/get-suggestion/' + suggestionId, {headers: {'Authorization': "Bearer " + token}});
};

export const getVotingForUser = function (userId) {
    let token = getToken();
    return instance.get('api/voting/get-user/' + userId, {headers: {'Authorization': "Bearer " + token}});
};

export const getUser = function (userId) {
    return instance.get('api/user/get/' + userId);
};

export const getAllTags = function () {
    return instance.get('api/tag/get-all/');
};

export const getAllTagsForSuggestion = function (suggestionId) {
    return instance.get('api/tag/get-tag/' + suggestionId);
};

export const addTag = function (tag) {
    return instance.post('api/tag/add/', tag);
};

export const addTagToSuggestion = function (tagId, suggestionId) {
    return instance.post('api/tag/add-tag-suggestion/' + tagId + '/' + suggestionId);
};

export const removeTagToSuggestion = function (tagId, suggestionId) {
    return instance.post('api/tag/remove-tag-suggestion/' + tagId + '/' + suggestionId);
};
