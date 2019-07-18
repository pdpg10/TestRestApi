import com.google.gson.Gson

fun main() {
    val gson = Gson()
    val employee = GetEmployee(
        "111",
        "user",
        "100",
        "24",
        ""
    )
    val strJson = gson.toJson(employee)
    //.......

    val newEmployee = gson.fromJson(strJson,GetEmployee::class.java)
    println(newEmployee)

    println(strJson)
}