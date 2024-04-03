export default {
    content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
      fontFamily: {
        sans: "Helvetica, Arial, sans-serif",
      },
    },
    daisyui: {
      themes: [
        {
          light: {
            primary: "#a8cb34",
            secondary: "#59464c",
            accent: "#c2db71",
            neutral: "#262626",
            "base-100": "#f6f6f6",
            error: "#ff0000",
            "--new": "#EDF4D6",
            "--label": "#999999",
            ...require("daisyui/src/theming/themes")["[data-theme=light"],
            "--primary-muted": "#f6f6f6",
            "--option-text": "#262626",
          },
          dark: {
            primary: "#a8cb34",
            secondary: "#b9a6ac",
            accent: "#8fad2c",
            neutral: "#f6f6f6",
            "base-100": "#1e1e1e",
            error: "#ff0000",
            "--new": "#435114",
            "--label": "#cccccc",
            ...require("daisyui/src/theming/themes")["[data-theme=light"],
            "--primary-muted": "#1e1e1e",
            "--option-text": "#f6f6f6",
          },
        },
      ],
    },
    plugins: [require('daisyui')],
  };