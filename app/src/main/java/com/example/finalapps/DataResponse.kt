package com.example.finalapps

data class DataResponse(
    var id : Int,
    var name : String,
    var username : String,
    var email : String,
    var password : String,
    var createdAt : String,
    var updatedAt : String,
    var token : String
)
data class personRespon(
        var id : Int,
        var nama : String,
        var kode : Int,
        var createdAt : String,
        var updatedAt : String
)
data class SingleRespon<T> (
    var msg: String,
    var status : Int,
    var data : T
)
data class ListResponse<T>(
        var msg : String,
        var status : Int,
        var data : List<T>
)