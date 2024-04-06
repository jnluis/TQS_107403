/** @type {import('tailwindcss').Config} */

export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ], theme: {
    fontFamily: {
      sans: "Helvetica, Arial, sans-serif",
    },
  },
  plugins: [require('daisyui')],
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
        },
        dark: {
          primary: "#a8cb34",
          secondary: "#b9a6ac",
          accent: "#8fad2c",
          neutral: "#f6f6f6",
          "base-100": "#1e1e1e",
          error: "#ff0000",
        },
      },
    ],
  },
};

