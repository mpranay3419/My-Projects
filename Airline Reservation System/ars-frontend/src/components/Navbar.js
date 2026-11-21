import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const user = JSON.parse(localStorage.getItem("user"));

    const isAdmin = user?.role === "ADMIN";

    const logout = () => {

        localStorage.removeItem("user");
        navigate("/");

    };

    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">

            <div className="container">

                <Link className="navbar-brand fw-bold fs-3" to="/search">

                    Airline Reservation System

                </Link>

                {

                    user &&

                    <div className="ms-auto d-flex align-items-center">

                        {
                            isAdmin ?

                                <>

                                    <Link className="btn btn-primary text-white me-2" to="/admin">

                                        Dashboard

                                    </Link>

                                    <Link className="btn btn-primary text-white me-2" to="/admin/airports">

                                        Airports

                                    </Link>

                                    <Link className="btn btn-primary text-white me-2" to="/admin/airplanes">

                                        Airplanes

                                    </Link>

                                    <Link className="btn btn-primary text-white me-2" to="/admin/flights">

                                        Flights

                                    </Link>

                                    <Link className="btn btn-primary text-white me-3" to="/admin/bookings">

                                        Bookings

                                    </Link>

                                </>

                                :

                                <>

                                    <Link className="btn btn-primary text-white me-2" to="/search">

                                        Home

                                    </Link>

                                    <Link className="btn btn-primary text-white me-2" to="/search">

                                        Search Flights

                                    </Link>

                                    <Link className="btn btn-primary text-white me-2" to="/bookings">

                                        My Bookings

                                    </Link>

                                    <Link className="btn btn-primary text-white me-3" to="/wallet">

                                         ₹{user.walletBalance}

                                    </Link>

                                </>

                        }

                        <button
                            className="btn btn-danger"
                            onClick={logout}
                        >

                            Logout

                        </button>

                    </div>
                }

            </div>

        </nav>

    );

}

export default Navbar;