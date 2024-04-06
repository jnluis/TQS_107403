import missing from "../../public/missing.svg";

const MissingPage = () => {
    return (
      <main>
        <section className="flex flex-col items-center justify-center w-full h-screen font-semibold">
          <h1 className="text-3xl text-center text-primary font">
            Ops! A página que procura não foi encontrada. <br />
            <img src={missing}  alt="404" />
          </h1>
        </section>
      </main>
    );
  };
  
  export default MissingPage;