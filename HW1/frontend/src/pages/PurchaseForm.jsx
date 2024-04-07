function PurchaseForm() {
    return (
      <div className="max-w-md mx-auto p-6 rounded-md shadow-md">
        <form className="space-y-4">
          <div className="flex flex-col">
            <label htmlFor="name" className="text-sm font-semibold">
              First Name
            </label>
            <input
              id="FirstName"
              name="FirstName"
              type="text"
              className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:border-primary"
            />
          </div>
          <div className="flex flex-col">
            <label htmlFor="name" className="text-sm font-semibold">
              Last Name
            </label>
            <input
              id="LastName"
              name="name"
              type="text"
              className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:border-primary"
            />
          </div>
          <div className="flex flex-col">
            <label htmlFor="email" className="text-sm font-semibold">
              City
            </label>
            <input
              id="city"
              name="city"
              type="text"
              className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:border-primary"
            />
          </div>
          <div className="flex flex-col">
            <label htmlFor="email" className="text-sm font-semibold">
              Email
            </label>
            <input
              id="email"
              name="email"
              type="text"
              className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:border-primary"
            />
          </div>
        </form>
      </div>
    );
  }
  
  export default PurchaseForm;