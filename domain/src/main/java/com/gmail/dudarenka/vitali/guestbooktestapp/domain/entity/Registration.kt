package com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity

data class Registration(val name: String, val password: String, val email: String, val filePath: String) : DomainEntity