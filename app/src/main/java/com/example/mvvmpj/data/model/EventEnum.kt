package com.example.mvvmpj.data.model

enum class EventEnum(var msg : String) {
    AddBookMark("북마크에 추가 되었습니다."),
    ErrorAddBookMark("이미 추가된 책입니다."),
    DeleteBookMark("북마크가 삭제 되었습니다."),
    ErrorDeleteBookMark("이미 삭제된 책입니다.")
}