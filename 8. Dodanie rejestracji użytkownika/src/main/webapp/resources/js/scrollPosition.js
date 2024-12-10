function saveScrollPosition() {
    sessionStorage.setItem('scrollPosition', window.scrollY);
}

window.addEventListener('DOMContentLoaded', function () {
    var scrollPosition = sessionStorage.getItem('scrollPosition');
    if (scrollPosition) {
        document.documentElement.style.scrollBehavior = 'auto'; // Wyłącza animację przewijania
        window.scrollTo(0, scrollPosition); // Ustawia pozycję scrolla
        sessionStorage.removeItem('scrollPosition'); // Usuwa zapis po użyciu
    }
});

