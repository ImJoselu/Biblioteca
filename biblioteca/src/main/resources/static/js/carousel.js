var carousel = document.getElementById("carousel");
var images = carousel.getElementsByTagName("img");
var current = 0;

function next() {
  images[current].style.opacity = 0;
  current = (current + 1) % images.length;
  images[current].style.opacity = 1;
}

setInterval(next, 10000);