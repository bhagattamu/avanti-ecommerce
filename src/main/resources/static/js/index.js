

// Toast

const toastContainer = document.getElementById("liveToast");
if (toastContainer) {
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastContainer);
    toastBootstrap.show();
}
