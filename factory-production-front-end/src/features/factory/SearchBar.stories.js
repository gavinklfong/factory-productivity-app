import { Box, Grommet } from "grommet";
import { grommet } from "grommet/themes";
import { SearchBar } from "./SearchBar";

export default {
    title: 'SearchBar',
    component: SearchBar
}

export const Default = () => (
  <Grommet theme={grommet}>
    <Box align="center" pad="large">
      <SearchBar />
    </Box>
  </Grommet>
);