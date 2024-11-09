export const fetchTotalPrice = createAsyncThunk(
    'cart/fetchTotalPrice',
    async ({ userId }, { rejectWithValue }) => {
      try {
        const res = await axios.get(`http://localhost:8086/cart/getTotalPrice/${userId}`);
        console.log("TotalPrice data: ", res);
  
        // Assuming the total price is in result[0] based on your response
        if (res.data.statusCode === 200 && res.data.result && res.data.result.length > 0) {
          return res.data.result[0]; // Return the first item in the result array
        } else {
          return rejectWithValue('No total price available or invalid response.');
        }
      } catch (error) {
        console.log("TotalPrice error: ", error);
        return rejectWithValue(error.response?.data || 'Failed to fetch total price');
      }
    }
  );

  

  export const displayTotalpriceSlice = createSlice({
    name: 'priceCart',
    initialState: {
      totalPrice: null,
      loading: false,
      error: null,
    },
    reducers: {},
    extraReducers: (builder) => {
      builder
        .addCase(fetchTotalPrice.pending, (state) => {
          state.loading = true;
          state.error = null;
        })
        .addCase(fetchTotalPrice.fulfilled, (state, action) => {
          state.loading = false;
          state.totalPrice = action.payload; // Store the fetched total price here
        })
        .addCase(fetchTotalPrice.rejected, (state, action) => {
          state.loading = false;
          state.error = action.payload || 'Failed to fetch total price';
        });
    }
  });

  

  const { totalPrice, loading, error } = useSelector((state) => state.priceCart);

return (
  <div>
    {loading ? (
      <p>Loading ....</p>
    ) : error ? (
      <p>{error}</p>
    ) : totalPrice !== null ? (
      <span>{totalPrice}</span> // Display the total price
    ) : (
      <p>No total price available</p> // Handle case where no price is available
    )}
  </div>
);
