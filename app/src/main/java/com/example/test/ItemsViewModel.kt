package com.example.test
class ItemsViewModel {
    var title: String? = ""
    var description:String? = ""
    var image : String? = ""

    constructor(title: String,description:String,image:String) {
        this.title = title
        this.description = description
        this.image = image
    }

    constructor()

}
