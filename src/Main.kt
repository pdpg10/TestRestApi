import com.google.gson.Gson
import com.sun.xml.internal.bind.v2.model.core.ID
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

val httpClient = OkHttpClient.Builder()
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()
//apache http  client
// kotlin fuel
val gson = Gson()
//moshi
//jackson

val JSON = "application/json; charset=utf-8".toMediaType()

fun main() {

}

fun deleteEmployee(id: Int) {
    val request = Request.Builder()
        .url("$BASE_URL$DELETE_EMPLOYEE$id")
        .delete()
        .build()

    val response = httpClient.newCall(request)
        .execute()
    if (response.isSuccessful) {
        val jsonStr = response.body?.string()
        println(jsonStr)
    } else {
        println(response.message)
    }
}

fun updateEmployee(id: Int) {

    val updatedEmployee = PostEmployee("UpdateName", "10012", "22")
    val updatedEmployeeJson = gson.toJson(updatedEmployee)
    val reqBody = updatedEmployeeJson.toRequestBody(JSON)
    val request = Request.Builder()
        .url("$BASE_URL$UPDATE_EMPLOYEE$id")
        .put(reqBody)
        .build()

    val response = httpClient.newCall(request)
        .execute()
    if (response.isSuccessful) {
        val jsonStr = response.body?.string()
        val employee: PostEmployee = gson.fromJson(jsonStr, PostEmployee::class.java)
        println(employee)
    } else {
        println(response.message)
    }
}

fun loadEmployeeById(id: Int) {
    val request = Request.Builder()
        .url("$BASE_URL$GET_EMPLOYEE_BY_ID$id")
        .build()

    val response = httpClient.newCall(request)
        .execute()
    if (response.isSuccessful) {
        val jsonStr = response.body?.string()
        val employee: GetEmployee = gson.fromJson(jsonStr, GetEmployee::class.java)
        println(employee)
    } else {
        println(response.message)
    }
}

fun createEmployee() {
    val newEmployee = PostEmployee(
        "testName",
        "100_00",
        "30"
    )
    val employeeJson: String = gson.toJson(newEmployee)
    println(employeeJson)
    val reqBody: RequestBody = employeeJson.toRequestBody(JSON)
    val request = Request.Builder()
        .url("$BASE_URL$CREATE_EMPLOYEE")
        .post(reqBody)
        .build()

    val res = httpClient.newCall(request).execute()
    if (res.isSuccessful) {
        val strBody = res.body?.string()
        println(strBody)
    } else {
        println(res.message)
    }
}

fun loadEmployees() {
    val request = Request.Builder()
        .url("$BASE_URL$GET_EMPLOYEE")
        .build()

    val response = httpClient.newCall(request)
        .execute()
    if (response.isSuccessful) {
        val jsonStr = response.body?.string()
        val employeeArray: Array<GetEmployee> = gson
            .fromJson(jsonStr, Array<GetEmployee>::class.java)
        for (it in employeeArray) {
            println(it.employee_name)
        }
    } else {
        println(response.message)
    }
}

