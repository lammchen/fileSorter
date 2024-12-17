browser.contextMenus.create({
    id: "save-media-to-db",
    title: "Save Media to DB",
    contexts: ["image", "video"]
  });
  
  browser.contextMenus.onClicked.addListener((info, tab) => {
    if (info.menuItemId === "save-media-to-db") {
      const sourceUrl = info.srcUrl;
      const apiUrl = "http://localhost:8080/api/media";
  
      const payload = {
        name: sourceUrl.split('/').pop(),
        labels: "auto,save",
        collection: "browser-save",
        sourceUrl: sourceUrl
      };
  
      fetch(apiUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      })
      .then(response => {
        if (response.ok) {
          alert("Media saved successfully!");
        } else {
          alert("Failed to save media.");
        }
      })
      .catch(error => {
        console.error("Error:", error);
        alert("Error while saving media.");
      });
    }
  });
  