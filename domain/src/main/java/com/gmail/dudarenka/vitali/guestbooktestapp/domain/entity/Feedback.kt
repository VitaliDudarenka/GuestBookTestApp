package com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity

data class Feedback(val comment_id: String?, val title: String?, val message: String?, val created_at: String?, val user: User?) : DomainEntity