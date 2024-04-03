import React from 'react';
import './App.css';

function Home() {
    return (
        <>
            <div className="jumbotron bg-white p-8 mt-4">
                <h1 className="text-4xl font-bold">Welcome to the Simple Travel Agency!</h1>
                <p className="mt-4">This is a sample site you can test with BlazeMeter!</p>
                <p className="mt-2">Check out our <a href="vacation.html" className="text-blue-500">destination of the week! The Beach!</a></p>
            </div>
            <div className="container mx-auto p-4">
                <h2 className="text-2xl">Choose your departure city:</h2>
                <form action="reserve.php" method="post" className="mt-4">
                    {/* Departure City Select */}
                    <select name="fromPort" className="form-control">
                        {/* Options */}
                    </select>

                    <h2 className="text-2xl mt-4">Choose your destination city:</h2>
                    {/* Destination City Select */}
                    <select name="toPort" className="form-control mt-2">
                        {/* Options */}
                    </select>

                    <div className="mt-4">
                        <input type="submit" value="Find Flights" className="btn bg-blue-500 text-white p-2" />
                    </div>
                </form>
            </div>
        </>
    );
}

export default Home;