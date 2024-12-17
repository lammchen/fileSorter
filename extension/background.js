browser.contextMenus.create({
  id: "save-media-to-db",
  title: "Save Media to DB",
  contexts: ["image", "video", "audio"]
});

let sourceUrl;

browser.contextMenus.onClicked.addListener((info, tab) => {
  if (info.menuItemId === "save-media-to-db") {
      sourceUrl = info.srcUrl;

      // Opening popup
      browser.windows.create({
          url: browser.runtime.getURL("popup.html"),
          type: "popup",
          width: 400,
          height: 300
      });
  }
});

// Transmit infos from popup to API
browser.runtime.onMessage.addListener((message) => {
  const apiUrl = "http://localhost:410/api/media";

  const payload = {
      name: message.name || sourceUrl.split('/').pop(),
      labels: message.labels,
      collection: message.collection,
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
});
