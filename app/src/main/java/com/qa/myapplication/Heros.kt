package com.qa.myapplication

data class Heros(
    // must match the json keys e.g.
    // name: "Spider Man"
    var name: String,
    var realname: String,
    var team: String,
    var firstappearance: String,
    var createdby: String,
    var publisher: String,
    var imageurl: String,
    var bio: String
)