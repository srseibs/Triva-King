package com.sailinghawklabs.triviaking.data.remote.dto

object ResponseCode {

    const val SUCCESS = 0


    fun toMessage(code: Int): String {

        // from: https://opentdb.com/api_config.php

        return when(code) {
            0 -> { "Success: Returned results successfully." }
            1 -> { "No Results: Could not return results. The API doesn't have enough questions for your query. (Ex. Asking for 50 Questions in a Category that only has 20.)"}
            2 -> { "Invalid Parameter: Contains an invalid parameter. Arguments passed in aren't valid. (Ex. Amount = Five)"}
            3 -> { "Token Not Found: Session Token does not exist."}
            4 -> { "Token Empty: Session Token has returned all possible questions for the specified query. Resetting the Token is necessary." }
            else -> { "Unrecognized code."}
        }
    }

}