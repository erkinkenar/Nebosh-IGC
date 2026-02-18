package com.nebosh.igc.ui

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Topics : Screen("topics/{elementId}") {
        fun createRoute(elementId: String) = "topics/$elementId"
    }
    object SubTopics : Screen("subtopics/{chapterId}") {
        fun createRoute(chapterId: String) = "subtopics/$chapterId"
    }
    object Flashcards : Screen("flashcards/{topicId}") {
        fun createRoute(topicId: String) = "flashcards/$topicId"
    }
    object Quiz : Screen("quiz/{topicId}") {
        fun createRoute(topicId: String) = "quiz/$topicId"
    }
    object OrderingGame : Screen("ordering_game")
    object Glossary : Screen("glossary")
    object Settings : Screen("settings")
    object RiskCalculator : Screen("risk_calculator")
}