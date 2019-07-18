data class GetEmployee(
    val id: String,
    val employee_name: String,
    val employee_salary: String,
    val employee_age: String,
    val profile_image: String
)//todo gson serializename

data class PostEmployee(
    val name: String,
    val salary: String,
    val age: String
)

